var AppDispatcher = require('../dispatcher/AppDispatcher');
var Constants = require('../constants/Constants');
var ErrorAction = require('./ErrorAction');
var ApiConst = require('../constants/GithubApiConstants');
var BrowseConst = ApiConst.BROWSE_CONSTANTS;

var commitsCounter = 0;

function buildResponse(repository, commits, committers) {
    "use strict";
    var data = repository;
    data.commits = commits;
    data.committers = committers;
    commitsCounter += commits.length;
    return data;
}

var RepositoryAction = {

    loadRepositoryByName: function (ownerName, repositoryName) {
        "use strict";
        commitsCounter = 0;
        var repoPath = BrowseConst.SERVER_API_PATH + '/owner/' + ownerName + '/repository/' + repositoryName;
        $.ajax({
            url: repoPath,
            type: 'GET',
            contentType: 'application/json; charset=utf-8'
        })
            .done(function (data, textStatus, xhr) {
                AppDispatcher.dispatch({
                    actionType: Constants.LOAD_REPOSITORY_BY_NAME,
                    data: data
                });
            })
            .fail(function (xhr, textStatus, error) {
                ErrorAction.showError(xhr.status, xhr.responseJSON.message);
            });
    }
};

module.exports = RepositoryAction;