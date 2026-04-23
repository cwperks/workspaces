package org.opensearch.workspaces.action;
import java.io.IOException;
import org.opensearch.core.action.ActionResponse;
import org.opensearch.core.common.io.stream.StreamInput;
import org.opensearch.core.common.io.stream.StreamOutput;
import org.opensearch.core.xcontent.ToXContentObject;
import org.opensearch.core.xcontent.XContentBuilder;
public class DeleteWorkspaceResponse extends ActionResponse implements ToXContentObject {
    private final boolean deleted;
    public DeleteWorkspaceResponse(boolean deleted) { this.deleted = deleted; }
    public DeleteWorkspaceResponse(StreamInput in) throws IOException { super(in); deleted = in.readBoolean(); }
    @Override public void writeTo(StreamOutput out) throws IOException { out.writeBoolean(deleted); }
    @Override public XContentBuilder toXContent(XContentBuilder builder, Params params) throws IOException {
        return builder.startObject().field("deleted", deleted).endObject();
    }
}
