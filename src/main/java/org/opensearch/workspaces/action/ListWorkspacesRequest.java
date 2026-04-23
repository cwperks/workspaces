package org.opensearch.workspaces.action;
import java.io.IOException;
import org.opensearch.action.ActionRequest;
import org.opensearch.action.ActionRequestValidationException;
import org.opensearch.core.common.io.stream.StreamInput;
import org.opensearch.core.common.io.stream.StreamOutput;
public class ListWorkspacesRequest extends ActionRequest {
    public ListWorkspacesRequest() {}
    public ListWorkspacesRequest(StreamInput in) throws IOException { super(in); }
    @Override public ActionRequestValidationException validate() { return null; }
    @Override public void writeTo(StreamOutput out) throws IOException { super.writeTo(out); }
}
