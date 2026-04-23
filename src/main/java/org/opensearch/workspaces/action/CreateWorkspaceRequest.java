package org.opensearch.workspaces.action;
import java.io.IOException;
import java.util.List;
import org.opensearch.action.ActionRequest;
import org.opensearch.action.ActionRequestValidationException;
import org.opensearch.core.common.io.stream.StreamInput;
import org.opensearch.core.common.io.stream.StreamOutput;
public class CreateWorkspaceRequest extends ActionRequest {
    private final String name;
    private final String description;
    private final List<String> features;
    private final String color;
    public CreateWorkspaceRequest(String name, String description, List<String> features, String color) {
        this.name = name; this.description = description; this.features = features; this.color = color;
    }
    public CreateWorkspaceRequest(StreamInput in) throws IOException {
        super(in); name = in.readString(); description = in.readOptionalString();
        features = in.readStringList(); color = in.readOptionalString();
    }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public List<String> getFeatures() { return features; }
    public String getColor() { return color; }
    @Override public ActionRequestValidationException validate() { return null; }
    @Override public void writeTo(StreamOutput out) throws IOException {
        super.writeTo(out); out.writeString(name); out.writeOptionalString(description);
        out.writeStringCollection(features); out.writeOptionalString(color);
    }
}
