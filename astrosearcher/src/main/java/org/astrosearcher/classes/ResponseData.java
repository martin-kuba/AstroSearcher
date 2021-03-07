package org.astrosearcher.classes;

import lombok.Getter;
import lombok.Setter;
import org.astrosearcher.classes.mast.MastResponse;

@Getter
@Setter
public class ResponseData {
    private MastResponse mastResponse = new MastResponse();

    public ResponseData() {}

    public ResponseData(MastResponse mastResponse) {
        this.mastResponse = mastResponse;
    }

    public boolean isRetrieved() {
        return mastResponse != null;
    }
}
