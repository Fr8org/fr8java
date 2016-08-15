package terminal;

import co.fr8.data.interfaces.dto.ActivityDTO;
import co.fr8.data.interfaces.dto.Fr8DataDTO;
import co.fr8.terminal.base.ActionNameEnum;
import co.fr8.util.json.JsonUtils;
import co.fr8.util.logging.Logger;
import com.fasterxml.jackson.databind.JsonNode;
import github.GitHubTerminal;
import org.apache.commons.lang3.StringUtils;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.Iterator;

public class GithubTerminalTest extends Controller {

  /**
   * Test the /terminals/activate path
   * @return a JSON string
   */
  public Result activateTest() {
    ActivityDTO activityDTO =
        JsonUtils.writeStringToActivityDTO(ACTIVATE_INPUT);

    return processDTO(activityDTO, CID, ActionNameEnum.ACTIVATE);

  }

  /**
   * Test the /activities/configure path
   * @return a JSON String
   */
  public Result configTest() {
    GitHubTerminal terminal = new GitHubTerminal();

    ActivityDTO activityDTO =
        JsonUtils.writeStringToActivityDTO(CONFIGURE_INPUT);
    return processDTO(activityDTO, CID, ActionNameEnum.CONFIGURE);
  }

  public Result configGetTest() {
    JsonNode requestJson = JsonUtils.writeStringToObject(CONFIGURE_GET_REPOS_INPUT);

    if (Logger.isDebugEnabled() && requestJson != null) {
      Iterator<String> jsonFields = requestJson.fieldNames();

      jsonFields.forEachRemaining(field -> {
        Logger.debug("++++ testing JSON parsing: " + field + " " + requestJson.get(field));
      });

    }

    ActivityDTO activityDTO =
        JsonUtils.writeStringToActivityDTO(requestJson.get("ActivityDTO").toString());

    return processDTO(activityDTO, requestJson.get("ContainerId").toString(), ActionNameEnum.CONFIGURE);
  }

  public Result activateGetTest() {
    JsonNode requestJson = JsonUtils.writeStringToObject(ACTIVATE_GET_REPOS_INPUT);

    ActivityDTO dto = JsonUtils.writeStringToActivityDTO(requestJson.get("ActivityDTO").toString());

    return processDTO(dto, requestJson.get("ContainerId").asText(), ActionNameEnum.ACTIVATE);
  }

  private Result processDTO(ActivityDTO activityDTO, String containerId, ActionNameEnum actionName) {
    GitHubTerminal terminal = new GitHubTerminal();

    ActivityDTO resultDTO = terminal.handleFr8Request(actionName, null,
        new Fr8DataDTO(activityDTO,
            (StringUtils.isBlank(containerId) || "null".equalsIgnoreCase(containerId) ? CID : containerId)),
            request().queryString());

    String responseString = JsonUtils.writeObjectAsString(resultDTO);

    Logger.debug("handleFr8Request returned: " + responseString);

    return ok(responseString);

  }

  private static final String CID = "00000000-0000-0000-0000-000000000000";

  private static final String CONFIGURE_INPUT = "{\"Label\":null,\"Name\":\"Subscribe to GitHub Repository\",\"activityTemplate\":{\"id\":\"f5af5dfb-0a67-416c-99b9-611485fd11ca\",\"name\":\"List GitHub Repositories Activity\",\"version\":\"1\",\"label\":\"Subscribe to GitHub Repository\",\"terminal\":{\"name\":\"terminalGithub\",\"label\":\"GitHub\",\"version\":\"1\",\"endpoint\":\"http://localhost:9000\"},\"tags\":null,\"category\":\"Monitors\",\"type\":\"Standard\",\"minPaneWidth\":380,\"needsAuthentication\":true,\"webService\":{\"id\":17,\"name\":\"GitHub\",\"iconPath\":\"https://assets-cdn.github.com/favicon.ico\"}},\"RootPlanNodeId\":\"d833b668-3d49-4d98-8109-692183ad8cf7\",\"ParentPlanNodeId\":\"5dcea9d5-db98-46af-ac1a-0f4ce3eea38d\",\"CurrentView\":null,\"Ordering\":1,\"Id\":\"76f69cd4-15dd-449e-a8bb-92e572a4c715\",\"CrateStorage\":{\"crates\":[]},\"ChildrenActivities\":[],\"AuthTokenId\":null,\"AuthToken\":{\"Id\":\"48479d3f-221d-4e30-b353-c1c0d620084a\",\"Token\":\"baba152a468bdac589d45d3a9083ade4d5e04849\",\"ExternalAccountId\":\"3037127\",\"ExternalAccountName\":\"3037127\",\"ExternalDomainId\":null,\"ExternalDomainName\":null,\"UserId\":\"a90aee0a-7c7b-4501-bede-f8ab20325b61\",\"ExternalStateToken\":null,\"AdditionalAttributes\":null,\"Error\":null,\"AuthCompletedNotificationRequired\":false,\"TerminalID\":0},\"documentation\":null}\n";

  private static final String ACTIVATE_INPUT = "{\"Label\":null,\"Name\":\"Subscribe to GitHub Repository\",\"activityTemplate\":{\"id\":\"f5af5dfb-0a67-416c-99b9-611485fd11ca\",\"name\":\"List GitHub Repositories Activity\",\"version\":\"1\",\"label\":\"Subscribe to GitHub Repository\",\"terminal\":{\"name\":\"terminalGithub\",\"label\":\"GitHub\",\"version\":\"1\",\"endpoint\":\"http://localhost:9000\"},\"tags\":null,\"category\":\"Monitors\",\"type\":\"Standard\",\"minPaneWidth\":380,\"needsAuthentication\":true,\"webService\":{\"id\":17,\"name\":\"GitHub\",\"iconPath\":\"https://assets-cdn.github.com/favicon.ico\"}},\"RootPlanNodeId\":\"88f74059-998e-45c4-8f2e-e2511da09dd1\",\"ParentPlanNodeId\":\"b64f4a8d-1dbe-4086-bb1c-b8c1677f50be\",\"CurrentView\":null,\"Ordering\":1,\"Id\":\"8c4fb138-2fea-4cdd-ab2a-436a2b150502\",\"CrateStorage\":{\"crates\":[{\"manifestType\":\"Standard UI Controls\",\"manifestId\":6,\"manufacturer\":null,\"manifestRegistrar\":\"www.fr8.co/registry\",\"id\":\"87257f6e-2af3-44f6-a8e1-c07c2b327f54\",\"label\":\"Configuration_Controls\",\"contents\":{\"Controls\":[{\"listItems\":[{\"selected\":false,\"key\":\"AFNetworking\",\"value\":\"cpretzer/AFNetworking\"},{\"selected\":false,\"key\":\"congress\",\"value\":\"cpretzer/congress\"},{\"selected\":false,\"key\":\"huginn\",\"value\":\"cpretzer/huginn\"},{\"selected\":false,\"key\":\"opencongress\",\"value\":\"cpretzer/opencongress\"},{\"selected\":false,\"key\":\"openstates\",\"value\":\"cpretzer/openstates\"},{\"selected\":false,\"key\":\"platform\",\"value\":\"cpretzer/platform\"},{\"selected\":false,\"key\":\"platform-client\",\"value\":\"cpretzer/platform-client\"},{\"selected\":false,\"key\":\"PocketHub\",\"value\":\"cpretzer/PocketHub\"},{\"selected\":false,\"key\":\"realm-cocoa\",\"value\":\"cpretzer/realm-cocoa\"},{\"selected\":false,\"key\":\"realm-java\",\"value\":\"cpretzer/realm-java\"},{\"selected\":false,\"key\":\"scout\",\"value\":\"cpretzer/scout\"}],\"selectedKey\":\"PocketHub\",\"hasRefreshButton\":false,\"selectedItem\":null,\"name\":null,\"required\":false,\"value\":\"cpretzer/PocketHub\",\"label\":\"Select a repository to monitor\",\"type\":\"DropDownList\",\"selected\":false,\"events\":[],\"source\":null,\"showDocumentation\":null,\"isHidden\":false,\"isCollapsed\":false}]},\"parentCrateId\":null,\"createTime\":\"\",\"availability\":null,\"sourceActivityId\":null},{\"manifestType\":\"Standard Event Subscription\",\"manifestId\":8,\"manufacturer\":null,\"manifestRegistrar\":\"www.fr8.co/registry\",\"id\":\"97f88c72-e4ba-478e-af3d-8633a03a6392\",\"label\":\"Standard Event Subscriptions\",\"contents\":{\"Subscriptions\":[\"GitHub repository monitor\"],\"Manufacturer\":\"GitHub\"},\"parentCrateId\":null,\"createTime\":\"\",\"availability\":null,\"sourceActivityId\":null},{\"manifestType\":\"Crate Description\",\"manifestId\":32,\"manufacturer\":null,\"manifestRegistrar\":\"www.fr8.co/registry\",\"id\":\"be61c514-1f76-4cd2-923c-ef20612c5892\",\"label\":null,\"contents\":{\"CrateDescriptions\":[{\"manifestId\":5,\"manifestType\":\"Standard Payload Data\",\"label\":\"Repository Properties\",\"producedBy\":\"List GitHub Repositories Activity\",\"selected\":false,\"availability\":2,\"fields\":[{\"key\":\"id\",\"value\":\"id\",\"label\":null,\"fieldType\":null,\"isRequired\":false,\"availability\":3},{\"key\":\"name\",\"value\":\"name\",\"label\":null,\"fieldType\":null,\"isRequired\":false,\"availability\":3},{\"key\":\"full_name\",\"value\":\"full_name\",\"label\":null,\"fieldType\":null,\"isRequired\":false,\"availability\":3},{\"key\":\"owner\",\"value\":\"owner\",\"label\":null,\"fieldType\":null,\"isRequired\":false,\"availability\":3},{\"key\":\"private\",\"value\":\"private\",\"label\":null,\"fieldType\":null,\"isRequired\":false,\"availability\":3},{\"key\":\"html_url\",\"value\":\"html_url\",\"label\":null,\"fieldType\":null,\"isRequired\":false,\"availability\":3},{\"key\":\"description\",\"value\":\"description\",\"label\":null,\"fieldType\":null,\"isRequired\":false,\"availability\":3},{\"key\":\"fork\",\"value\":\"fork\",\"label\":null,\"fieldType\":null,\"isRequired\":false,\"availability\":3},{\"key\":\"url\",\"value\":\"url\",\"label\":null,\"fieldType\":null,\"isRequired\":false,\"availability\":3},{\"key\":\"forks_url\",\"value\":\"forks_url\",\"label\":null,\"fieldType\":null,\"isRequired\":false,\"availability\":3},{\"key\":\"keys_url\",\"value\":\"keys_url\",\"label\":null,\"fieldType\":null,\"isRequired\":false,\"availability\":3},{\"key\":\"collaborators_url\",\"value\":\"collaborators_url\",\"label\":null,\"fieldType\":null,\"isRequired\":false,\"availability\":3},{\"key\":\"teams_url\",\"value\":\"teams_url\",\"label\":null,\"fieldType\":null,\"isRequired\":false,\"availability\":3},{\"key\":\"hooks_url\",\"value\":\"hooks_url\",\"label\":null,\"fieldType\":null,\"isRequired\":false,\"availability\":3},{\"key\":\"issue_events_url\",\"value\":\"issue_events_url\",\"label\":null,\"fieldType\":null,\"isRequired\":false,\"availability\":3},{\"key\":\"events_url\",\"value\":\"events_url\",\"label\":null,\"fieldType\":null,\"isRequired\":false,\"availability\":3},{\"key\":\"assignees_url\",\"value\":\"assignees_url\",\"label\":null,\"fieldType\":null,\"isRequired\":false,\"availability\":3},{\"key\":\"branches_url\",\"value\":\"branches_url\",\"label\":null,\"fieldType\":null,\"isRequired\":false,\"availability\":3},{\"key\":\"tags_url\",\"value\":\"tags_url\",\"label\":null,\"fieldType\":null,\"isRequired\":false,\"availability\":3},{\"key\":\"blobs_url\",\"value\":\"blobs_url\",\"label\":null,\"fieldType\":null,\"isRequired\":false,\"availability\":3},{\"key\":\"git_tags_url\",\"value\":\"git_tags_url\",\"label\":null,\"fieldType\":null,\"isRequired\":false,\"availability\":3},{\"key\":\"git_refs_url\",\"value\":\"git_refs_url\",\"label\":null,\"fieldType\":null,\"isRequired\":false,\"availability\":3},{\"key\":\"trees_url\",\"value\":\"trees_url\",\"label\":null,\"fieldType\":null,\"isRequired\":false,\"availability\":3},{\"key\":\"statuses_url\",\"value\":\"statuses_url\",\"label\":null,\"fieldType\":null,\"isRequired\":false,\"availability\":3},{\"key\":\"languages_url\",\"value\":\"languages_url\",\"label\":null,\"fieldType\":null,\"isRequired\":false,\"availability\":3},{\"key\":\"stargazers_url\",\"value\":\"stargazers_url\",\"label\":null,\"fieldType\":null,\"isRequired\":false,\"availability\":3},{\"key\":\"contributors_url\",\"value\":\"contributors_url\",\"label\":null,\"fieldType\":null,\"isRequired\":false,\"availability\":3},{\"key\":\"subscribers_url\",\"value\":\"subscribers_url\",\"label\":null,\"fieldType\":null,\"isRequired\":false,\"availability\":3},{\"key\":\"subscription_url\",\"value\":\"subscription_url\",\"label\":null,\"fieldType\":null,\"isRequired\":false,\"availability\":3},{\"key\":\"commits_url\",\"value\":\"commits_url\",\"label\":null,\"fieldType\":null,\"isRequired\":false,\"availability\":3},{\"key\":\"git_commits_url\",\"value\":\"git_commits_url\",\"label\":null,\"fieldType\":null,\"isRequired\":false,\"availability\":3},{\"key\":\"comments_url\",\"value\":\"comments_url\",\"label\":null,\"fieldType\":null,\"isRequired\":false,\"availability\":3},{\"key\":\"issue_comment_url\",\"value\":\"issue_comment_url\",\"label\":null,\"fieldType\":null,\"isRequired\":false,\"availability\":3},{\"key\":\"contents_url\",\"value\":\"contents_url\",\"label\":null,\"fieldType\":null,\"isRequired\":false,\"availability\":3},{\"key\":\"compare_url\",\"value\":\"compare_url\",\"label\":null,\"fieldType\":null,\"isRequired\":false,\"availability\":3},{\"key\":\"merges_url\",\"value\":\"merges_url\",\"label\":null,\"fieldType\":null,\"isRequired\":false,\"availability\":3},{\"key\":\"archive_url\",\"value\":\"archive_url\",\"label\":null,\"fieldType\":null,\"isRequired\":false,\"availability\":3},{\"key\":\"downloads_url\",\"value\":\"downloads_url\",\"label\":null,\"fieldType\":null,\"isRequired\":false,\"availability\":3},{\"key\":\"issues_url\",\"value\":\"issues_url\",\"label\":null,\"fieldType\":null,\"isRequired\":false,\"availability\":3},{\"key\":\"pulls_url\",\"value\":\"pulls_url\",\"label\":null,\"fieldType\":null,\"isRequired\":false,\"availability\":3},{\"key\":\"milestones_url\",\"value\":\"milestones_url\",\"label\":null,\"fieldType\":null,\"isRequired\":false,\"availability\":3},{\"key\":\"notifications_url\",\"value\":\"notifications_url\",\"label\":null,\"fieldType\":null,\"isRequired\":false,\"availability\":3},{\"key\":\"labels_url\",\"value\":\"labels_url\",\"label\":null,\"fieldType\":null,\"isRequired\":false,\"availability\":3},{\"key\":\"releases_url\",\"value\":\"releases_url\",\"label\":null,\"fieldType\":null,\"isRequired\":false,\"availability\":3},{\"key\":\"deployments_url\",\"value\":\"deployments_url\",\"label\":null,\"fieldType\":null,\"isRequired\":false,\"availability\":3},{\"key\":\"created_at\",\"value\":\"created_at\",\"label\":null,\"fieldType\":null,\"isRequired\":false,\"availability\":3},{\"key\":\"updated_at\",\"value\":\"updated_at\",\"label\":null,\"fieldType\":null,\"isRequired\":false,\"availability\":3},{\"key\":\"pushed_at\",\"value\":\"pushed_at\",\"label\":null,\"fieldType\":null,\"isRequired\":false,\"availability\":3},{\"key\":\"git_url\",\"value\":\"git_url\",\"label\":null,\"fieldType\":null,\"isRequired\":false,\"availability\":3},{\"key\":\"ssh_url\",\"value\":\"ssh_url\",\"label\":null,\"fieldType\":null,\"isRequired\":false,\"availability\":3},{\"key\":\"clone_url\",\"value\":\"clone_url\",\"label\":null,\"fieldType\":null,\"isRequired\":false,\"availability\":3},{\"key\":\"svn_url\",\"value\":\"svn_url\",\"label\":null,\"fieldType\":null,\"isRequired\":false,\"availability\":3},{\"key\":\"homepage\",\"value\":\"homepage\",\"label\":null,\"fieldType\":null,\"isRequired\":false,\"availability\":3},{\"key\":\"size\",\"value\":\"size\",\"label\":null,\"fieldType\":null,\"isRequired\":false,\"availability\":3},{\"key\":\"stargazers_count\",\"value\":\"stargazers_count\",\"label\":null,\"fieldType\":null,\"isRequired\":false,\"availability\":3},{\"key\":\"watchers_count\",\"value\":\"watchers_count\",\"label\":null,\"fieldType\":null,\"isRequired\":false,\"availability\":3},{\"key\":\"language\",\"value\":\"language\",\"label\":null,\"fieldType\":null,\"isRequired\":false,\"availability\":3},{\"key\":\"has_issues\",\"value\":\"has_issues\",\"label\":null,\"fieldType\":null,\"isRequired\":false,\"availability\":3},{\"key\":\"has_downloads\",\"value\":\"has_downloads\",\"label\":null,\"fieldType\":null,\"isRequired\":false,\"availability\":3},{\"key\":\"has_wiki\",\"value\":\"has_wiki\",\"label\":null,\"fieldType\":null,\"isRequired\":false,\"availability\":3},{\"key\":\"has_pages\",\"value\":\"has_pages\",\"label\":null,\"fieldType\":null,\"isRequired\":false,\"availability\":3},{\"key\":\"forks_count\",\"value\":\"forks_count\",\"label\":null,\"fieldType\":null,\"isRequired\":false,\"availability\":3},{\"key\":\"mirror_url\",\"value\":\"mirror_url\",\"label\":null,\"fieldType\":null,\"isRequired\":false,\"availability\":3},{\"key\":\"open_issues_count\",\"value\":\"open_issues_count\",\"label\":null,\"fieldType\":null,\"isRequired\":false,\"availability\":3},{\"key\":\"forks\",\"value\":\"forks\",\"label\":null,\"fieldType\":null,\"isRequired\":false,\"availability\":3},{\"key\":\"open_issues\",\"value\":\"open_issues\",\"label\":null,\"fieldType\":null,\"isRequired\":false,\"availability\":3},{\"key\":\"watchers\",\"value\":\"watchers\",\"label\":null,\"fieldType\":null,\"isRequired\":false,\"availability\":3},{\"key\":\"default_branch\",\"value\":\"default_branch\",\"label\":null,\"fieldType\":null,\"isRequired\":false,\"availability\":3},{\"key\":\"permissions\",\"value\":\"permissions\",\"label\":null,\"fieldType\":null,\"isRequired\":false,\"availability\":3}]}]},\"parentCrateId\":null,\"createTime\":\"\",\"availability\":null,\"sourceActivityId\":null}]},\"ChildrenActivities\":[],\"AuthTokenId\":\"48479d3f-221d-4e30-b353-c1c0d620084a\",\"AuthToken\":{\"Id\":\"48479d3f-221d-4e30-b353-c1c0d620084a\",\"Token\":\"baba152a468bdac589d45d3a9083ade4d5e04849\",\"ExternalAccountId\":\"3037127\",\"ExternalAccountName\":\"3037127\",\"ExternalDomainId\":null,\"ExternalDomainName\":null,\"UserId\":\"a90aee0a-7c7b-4501-bede-f8ab20325b61\",\"ExternalStateToken\":null,\"AdditionalAttributes\":null,\"Error\":null,\"AuthCompletedNotificationRequired\":false,\"TerminalID\":0},\"documentation\":null}";

  private static final String CONFIGURE_GET_REPOS_INPUT = "{\"ActivityDTO\":{\"Label\":null,\"Name\":\"Get all repositories\",\"activityTemplate\":{\"id\":\"4ce84447-14c9-453e-9c75-151a26e780dd\",\"name\":\"Git GET Example\",\"version\":\"1\",\"label\":\"Get all repositories\",\"terminal\":{\"name\":\"terminalGithub\",\"label\":\"GitHub\",\"version\":\"1\",\"endpoint\":\"http://localhost:9000\"},\"tags\":null,\"category\":\"Receivers\",\"type\":\"Standard\",\"minPaneWidth\":500,\"needsAuthentication\":false,\"webService\":{\"id\":17,\"name\":\"GitHub\",\"iconPath\":\"https://assets-cdn.github.com/favicon.ico\"}},\"RootPlanNodeId\":\"4aa2220c-bf59-4bbd-b7cf-fc5360e1903b\",\"ParentPlanNodeId\":\"b5a81f05-b244-44e6-8c0d-54afebe58959\",\"CurrentView\":null,\"Ordering\":1,\"Id\":\"732ae6a1-45ad-4d44-a902-30e7eb9ac34c\",\"CrateStorage\":{\"crates\":[]},\"ChildrenActivities\":[],\"AuthTokenId\":null,\"AuthToken\":{\"Id\":\"8c996817-257f-41f9-819d-c7978efdec2e\",\"Token\":\"baba152a468bdac589d45d3a9083ade4d5e04849\",\"ExternalAccountId\":\"3037127\",\"ExternalAccountName\":\"3037127\",\"ExternalDomainId\":null,\"ExternalDomainName\":null,\"UserId\":\"a90aee0a-7c7b-4501-bede-f8ab20325b61\",\"ExternalStateToken\":null,\"AdditionalAttributes\":null,\"Error\":null,\"AuthCompletedNotificationRequired\":false,\"TerminalID\":0},\"documentation\":null},\"ContainerId\":\"00000000-0000-0000-0000-000000000000\",\"ExplicitData\":null}";

  private static final String ACTIVATE_GET_REPOS_INPUT = "{\"ActivityDTO\":{\"Label\":null,\"Name\":\"Get all repositories\",\"activityTemplate\":{\"id\":\"4ce84447-14c9-453e-9c75-151a26e780dd\",\"name\":\"Git GET Example\",\"version\":\"1\",\"label\":\"Get all repositories\",\"terminal\":{\"name\":\"terminalGithub\",\"label\":\"GitHub\",\"version\":\"1\",\"endpoint\":\"http://localhost:9000\"},\"tags\":null,\"category\":\"Receivers\",\"type\":\"Standard\",\"minPaneWidth\":500,\"needsAuthentication\":true,\"webService\":{\"id\":17,\"name\":\"GitHub\",\"iconPath\":\"https://assets-cdn.github.com/favicon.ico\"}},\"RootPlanNodeId\":\"4aa2220c-bf59-4bbd-b7cf-fc5360e1903b\",\"ParentPlanNodeId\":\"b5a81f05-b244-44e6-8c0d-54afebe58959\",\"CurrentView\":null,\"Ordering\":1,\"Id\":\"12386521-552d-4422-b35d-19ba1eb2b201\",\"CrateStorage\":{\"crates\":[{\"manifestType\":\"Standard UI Controls\",\"manifestId\":6,\"manufacturer\":null,\"manifestRegistrar\":\"www.fr8.co/registry\",\"id\":\"0ada4b70-48dc-40e5-b408-0042a3cf6c4e\",\"label\":\"Configuration_Controls\",\"contents\":null,\"parentCrateId\":null,\"createTime\":\"2016-06-19T00:01:08.643-07:00\",\"availability\":null,\"sourceActivityId\":null},{\"manifestType\":\"Standard Event Subscription\",\"manifestId\":8,\"manufacturer\":null,\"manifestRegistrar\":\"www.fr8.co/registry\",\"id\":\"b96db193-7b28-4dae-89e0-a9ad8953d754\",\"label\":\"Standard Event Subscriptions\",\"contents\":null,\"parentCrateId\":null,\"createTime\":\"2016-06-19T00:01:08.643-07:00\",\"availability\":null,\"sourceActivityId\":null},{\"manifestType\":\"Crate Description\",\"manifestId\":32,\"manufacturer\":null,\"manifestRegistrar\":\"www.fr8.co/registry\",\"id\":\"ff1214f3-dd44-4f8b-9826-06a1dcb7f566\",\"label\":null,\"contents\":null,\"parentCrateId\":null,\"createTime\":\"2016-06-19T00:01:08.643-07:00\",\"availability\":null,\"sourceActivityId\":null}]},\"ChildrenActivities\":[],\"AuthTokenId\":\"8c996817-257f-41f9-819d-c7978efdec2e\",\"AuthToken\":{\"Id\":\"8c996817-257f-41f9-819d-c7978efdec2e\",\"Token\":\"baba152a468bdac589d45d3a9083ade4d5e04849\",\"ExternalAccountId\":\"3037127\",\"ExternalAccountName\":\"3037127\",\"ExternalDomainId\":null,\"ExternalDomainName\":null,\"UserId\":\"a90aee0a-7c7b-4501-bede-f8ab20325b61\",\"ExternalStateToken\":null,\"AdditionalAttributes\":null,\"Error\":null,\"AuthCompletedNotificationRequired\":false,\"TerminalID\":0},\"documentation\":null},\"ContainerId\":null,\"ExplicitData\":null}";
}
