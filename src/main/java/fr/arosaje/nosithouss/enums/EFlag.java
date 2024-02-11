package fr.arosaje.nosithouss.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EFlag {
    LAST_PAGE("last_page"),
    LAST_COMMON_NAME("last_common_name"),
    LAST_EXTRACTED_NUMBER("last_extracted_number");
    private final String key;
}
