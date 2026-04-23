package org.opensearch.workspaces.action;
import org.opensearch.action.ActionType;
public class DeleteWorkspaceAction extends ActionType<DeleteWorkspaceResponse> {
    public static final String NAME = "workspaces:workspace/delete";
    public static final DeleteWorkspaceAction INSTANCE = new DeleteWorkspaceAction();
    private DeleteWorkspaceAction() { super(NAME, DeleteWorkspaceResponse::new); }
}
