package org.opensearch.workspaces.action;
import org.opensearch.action.ActionType;
public class CreateWorkspaceAction extends ActionType<CreateWorkspaceResponse> {
    public static final String NAME = "workspaces:workspace/create";
    public static final CreateWorkspaceAction INSTANCE = new CreateWorkspaceAction();
    private CreateWorkspaceAction() { super(NAME, CreateWorkspaceResponse::new); }
}
