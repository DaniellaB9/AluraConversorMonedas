package principal;

import com.google.gson.annotations.SerializedName;

public record InfoCambio(
        @SerializedName("base_code") String monedaBase,
        @SerializedName("target_code") String monedaCambio,
        @SerializedName("conversion_result") double resultado) {
}
