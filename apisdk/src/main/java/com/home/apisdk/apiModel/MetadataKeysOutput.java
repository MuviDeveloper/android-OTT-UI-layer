package com.home.apisdk.apiModel;

import java.util.ArrayList;
import java.util.HashMap;

public class MetadataKeysOutput {
    ArrayList<String> metadata_name;
    HashMap<String, ArrayList<String>> all_metadata_info;


    public ArrayList<String> getMetadata_name() {
        return metadata_name;
    }

    public void setMetadata_name(ArrayList<String> metadata_name) {
        this.metadata_name = metadata_name;
    }


    public HashMap<String, ArrayList<String>> getAll_metadata_info() {
        return all_metadata_info;
    }

    public void setAll_metadata_info(HashMap<String, ArrayList<String>> all_metadata_info) {
        this.all_metadata_info = all_metadata_info;
    }

}
