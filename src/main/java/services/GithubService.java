package services;

import com.google.gson.Gson;
import models.Commit;
import models.Committer;
import models.Repository;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import models.Repositories;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


@Path("/github")
public class GithubService {

    private String GITHUB_URL = "https://api.github.com";
    private String SEARCH_REPO_URL = GITHUB_URL + "/search/repositories";
    private String REPO_URL = GITHUB_URL + "/repos";
    private int COMMIT_MAX = 100;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/repositories")
    public Response getRepositoriesByCriterias(@QueryParam("q") String q, @QueryParam("sort") String sort, @QueryParam("order") String order, @QueryParam("page") int page, @QueryParam("per_page") int perPage) {
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            List<NameValuePair> params = new LinkedList<NameValuePair>();
            params.add(new BasicNameValuePair("q", String.valueOf(q)));
            if (sort != null) params.add(new BasicNameValuePair("sort", String.valueOf(sort)));
            if (order != null) params.add(new BasicNameValuePair("order", String.valueOf(order)));
            params.add(new BasicNameValuePair("page", String.valueOf(page)));
            params.add(new BasicNameValuePair("perpage", String.valueOf(perPage)));
            String paramString = URLEncodedUtils.format(params, "utf-8");
            HttpGet request = new HttpGet(SEARCH_REPO_URL + "?" + paramString);
            request.addHeader("content-type", "application/json");
            HttpResponse result = httpClient.execute(request);
            String json = EntityUtils.toString(result.getEntity(), "UTF-8");

            Gson gson = new Gson();
            return Response.status(200).entity(gson.fromJson(json, Repositories.class)).build();

        } catch (IOException ex) {
            return Response.status(500).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/owner/{login}/repository/{name}")
    public Response getRepositoryByOwnerAndName(@PathParam("login")String ownerLogin, @PathParam("name")String repositoryName) {
        String repoPath = REPO_URL + "/" + ownerLogin + "/" + repositoryName;
        String commitPath = REPO_URL + "/" + ownerLogin + "/" + repositoryName + "/commits";
        String contributorsPath = REPO_URL + "/" + ownerLogin + "/" + repositoryName + "/contributors";
        Repository repository = getRepository(repoPath);
        addCommits(repository, commitPath);
        addContributors(repository, contributorsPath);

        return Response.status(200).entity(repository).build();
    }

    private void addContributors(Repository repository, String contributorsPath) {
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            HttpGet request = new HttpGet(contributorsPath);
            request.addHeader("content-type", "application/json");
            HttpResponse result = httpClient.execute(request);
            String json = EntityUtils.toString(result.getEntity(), "UTF-8");

            Gson gson = new Gson();
            repository.setCommiters(Arrays.asList(gson.fromJson(json, Committer[].class)));

        } catch (IOException ex) {
            repository.setCommiters(new ArrayList<Committer>());
        }
    }

    private void addCommits(Repository repository, String commitPath) {
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            List<NameValuePair> params = new LinkedList<NameValuePair>();
            params.add(new BasicNameValuePair("perpage", String.valueOf(COMMIT_MAX)));
            HttpGet request = new HttpGet(commitPath);
            request.addHeader("content-type", "application/json");
            HttpResponse result = httpClient.execute(request);
            String json = EntityUtils.toString(result.getEntity(), "UTF-8");

            Gson gson = new Gson();
            repository.setCommits(Arrays.asList(gson.fromJson(json, Commit[].class)));

        } catch (IOException ex) {
            repository.setCommits(new ArrayList<Commit>());
        }
    }

    private Repository getRepository(String repoPath) {
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            HttpGet request = new HttpGet(repoPath);
            request.addHeader("content-type", "application/json");
            HttpResponse result = httpClient.execute(request);
            String json = EntityUtils.toString(result.getEntity(), "UTF-8");

            Gson gson = new Gson();
            return gson.fromJson(json, Repository.class);

        } catch (IOException ex) {
            return new Repository();
        }
    }
}
