package org.opensearch.workspaces.action;
import org.opensearch.action.ActionType;
public class ListWorkspacesAction extends ActionType<ListWorkspacesResponse> {
    public static final String NAME = "workspaces:workspace/list";
    public static final ListWorkspacesAction INSTANCE = new ListWorkspacesAction();
    private ListWorkspacesAction() { super(NAME, ListWorkspacesResponse::new); }
}
