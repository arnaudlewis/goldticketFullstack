package services;

import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import models.Repositories;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;


@Path("/github")
public class GithubService {

    private String GITHUB_URL = "https://api.github.com";
    private String SEARCH_REPO_URL = GITHUB_URL + "/search/repositories";

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/repositories")
    public Response printMessage(@QueryParam("q") String q, @QueryParam("sort") String sort, @QueryParam("order") String order, @QueryParam("page") int page, @QueryParam("per_page") int perPage) {
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

}
