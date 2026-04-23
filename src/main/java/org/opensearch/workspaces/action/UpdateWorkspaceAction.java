package org.opensearch.workspaces.action;
import org.opensearch.action.ActionType;
public class UpdateWorkspaceAction extends ActionType<UpdateWorkspaceResponse> {
    public static final String NAME = "workspaces:workspace/update";
    public static final UpdateWorkspaceAction INSTANCE = new UpdateWorkspaceAction();
    private UpdateWorkspaceAction() { super(NAME, UpdateWorkspaceResponse::new); }
}
