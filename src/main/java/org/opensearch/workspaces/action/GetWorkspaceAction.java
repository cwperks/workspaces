package org.opensearch.workspaces.action;
import org.opensearch.action.ActionType;
public class GetWorkspaceAction extends ActionType<GetWorkspaceResponse> {
    public static final String NAME = "workspaces:workspace/get";
    public static final GetWorkspaceAction INSTANCE = new GetWorkspaceAction();
    private GetWorkspaceAction() { super(NAME, GetWorkspaceResponse::new); }
}
