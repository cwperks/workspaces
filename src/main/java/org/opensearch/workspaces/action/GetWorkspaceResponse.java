package org.opensearch.workspaces.action;
import java.io.IOException;
import org.opensearch.core.action.ActionResponse;
import org.opensearch.core.common.io.stream.StreamInput;
import org.opensearch.core.common.io.stream.StreamOutput;
import org.opensearch.core.xcontent.ToXContentObject;
import org.opensearch.core.xcontent.XContentBuilder;
import org.opensearch.workspaces.model.Workspace;
public class GetWorkspaceResponse extends ActionResponse implements ToXContentObject {
    private final Workspace workspace;
    public GetWorkspaceResponse(Workspace workspace) { this.workspace = workspace; }
    public GetWorkspaceResponse(StreamInput in) throws IOException { super(in); workspace = new Workspace(in); }
    @Override public void writeTo(StreamOutput out) throws IOException { workspace.writeTo(out); }
    @Override public XContentBuilder toXContent(XContentBuilder builder, Params params) throws IOException {
        return builder.startObject().field("workspace", workspace).endObject();
    }
}
