package team.dovecotmc.glasses.client.config;

import com.google.gson.annotations.SerializedName;

public class DisplayOffset {
    @SerializedName("x")
    public double x = .0;
    @SerializedName("y")
    public double y = .0;
    @SerializedName("z")
    public double z = .0;
    @SerializedName("x_rot")
    public float xRot = .0f;
    @SerializedName("y_rot")
    public float yRot = .0f;
    @SerializedName("z_rot")
    public float zRot = .0f;
    @SerializedName("x_scale")
    public float xScale = 1.0f;
    @SerializedName("y_scale")
    public float yScale = 1.0f;
    @SerializedName("z_scale")
    public float zScale = 1.0f;
}
