package service;

import model.URLInfo;
import service.URLInfoComparator;

/**
 * @author rajuraghuwanshi
 */
public class DefaultURLInfoComparator implements URLInfoComparator {

    public int compare(URLInfo o1, URLInfo o2) {
        if (o1.getTotalHits() < o2.getTotalHits())
            return -1;
        else if (o1.getTotalHits() > o2.getTotalHits())
            return 1;
        return 0;
    }
}
