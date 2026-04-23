/*
 * Copyright OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 */

package org.opensearch.workspaces.model;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.opensearch.core.common.io.stream.StreamInput;
import org.opensearch.core.common.io.stream.StreamOutput;
import org.opensearch.core.common.io.stream.Writeable;
import org.opensearch.core.xcontent.ToXContentObject;
import org.opensearch.core.xcontent.XContentBuilder;

public class Workspace implements Writeable, ToXContentObject {
    private final String id;
    private final String name;
    private final String description;
    private final String owner;
    private final long createdAt;
    private final long updatedAt;
    private final List<String> features;
    private final String color;

    public Workspace(String id, String name, String description, String owner,
                     long createdAt, long updatedAt, List<String> features, String color) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.owner = owner;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.features = features;
        this.color = color;
    }

    public Workspace(StreamInput in) throws IOException {
        id = in.readString();
        name = in.readString();
        description = in.readOptionalString();
        owner = in.readOptionalString();
        createdAt = in.readLong();
        updatedAt = in.readLong();
        features = in.readStringList();
        color = in.readOptionalString();
    }

    @Override
    public void writeTo(StreamOutput out) throws IOException {
        out.writeString(id);
        out.writeString(name);
        out.writeOptionalString(description);
        out.writeOptionalString(owner);
        out.writeLong(createdAt);
        out.writeLong(updatedAt);
        out.writeStringCollection(features);
        out.writeOptionalString(color);
    }

    @Override
    public XContentBuilder toXContent(XContentBuilder builder, Params params) throws IOException {
        builder.startObject();
        builder.field("id", id);
        builder.field("name", name);
        builder.field("description", description);
        builder.field("owner", owner);
        builder.field("createdAt", createdAt);
        builder.field("updatedAt", updatedAt);
        builder.field("features", features);
        builder.field("color", color);
        return builder.endObject();
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getOwner() { return owner; }
    public long getCreatedAt() { return createdAt; }
    public long getUpdatedAt() { return updatedAt; }
    public List<String> getFeatures() { return features; }
    public String getColor() { return color; }

    @SuppressWarnings("unchecked")
    public static Workspace fromSource(String id, Map<String, Object> source) {
        Map<String, Object> ws = (Map<String, Object>) source.getOrDefault("workspace", source);
        return new Workspace(
            id,
            (String) ws.getOrDefault("name", ""),
            (String) ws.getOrDefault("description", ""),
            (String) ws.getOrDefault("owner", ""),
            ((Number) ws.getOrDefault("createdAt", 0L)).longValue(),
            ((Number) ws.getOrDefault("updatedAt", 0L)).longValue(),
            (List<String>) ws.getOrDefault("features", List.of()),
            (String) ws.getOrDefault("color", "")
        );
    }

    public Map<String, Object> toSource() {
        return Map.of(
            "type", "workspace",
            "workspace", Map.of(
                "name", name,
                "description", description != null ? description : "",
                "owner", owner != null ? owner : "",
                "createdAt", createdAt,
                "updatedAt", updatedAt,
                "features", features != null ? features : List.of(),
                "color", color != null ? color : ""
            )
        );
    }
}
