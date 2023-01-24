package rxwriter.drug;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import rxwriter.drug.database.DrugRecord;
import rxwriter.drug.database.DrugSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DrugServiceTest //implements DrugSource
{

    @Test
    void drugsAreReturnSorted() {
        List<DrugRecord> drugsFound = new ArrayList<>();
        drugsFound.add(new DrugRecord("asmanex", new int[] {301}, 0));
        drugsFound.add(new DrugRecord("aspirin", new int[] {3645, 3530}, 0));
        DrugSource mockDrugSource = Mockito.mock(DrugSource.class);
        Mockito.when(mockDrugSource.findDrugsStartingWith("as")).thenReturn(drugsFound);
        DrugService service = new DrugService(mockDrugSource);
        List<DispensableDrug> foundDrugs = service.findDrugsStartingWith("as");
        assertNotNull(foundDrugs);
        assertEquals(2, foundDrugs.size());
        assertEquals("asmanex", foundDrugs.get(0).drugName());
        assertEquals("aspirin", foundDrugs.get(1).drugName());
    }

    @Test
    void throwsExceptionOnEmptyStartsWith() {
        List<DrugRecord> drugsFound = new ArrayList<>();
        DrugSource mockDrugSource = Mockito.mock(DrugSource.class);
        DrugService service = new DrugService(mockDrugSource);
        Exception thrown = assertThrows(IllegalArgumentException.class, () -> service.findDrugsStartingWith(" "));
        System.out.println(thrown.getMessage());
    }

    @Test
    void setsDrugPropertiesCorrectly() {
        List<DrugRecord> drugsFound = new ArrayList<>();
        drugsFound.add(new DrugRecord("aspirin", new int[] {3645, 3530}, 0));
        DrugSource mockDrugSource = Mockito.mock(DrugSource.class);
        Mockito.when(mockDrugSource.findDrugsStartingWith("aspirin")).thenReturn(drugsFound);
        DrugService service = new DrugService(mockDrugSource);

        List<DispensableDrug> foundDrugs = service.findDrugsStartingWith("aspirin");
        DrugClassification[] expectedClassifications = new DrugClassification[]{
                DrugClassification.ANALGESIC, DrugClassification.PLATELET_AGGREGATION_INHIBITORS
        };

        DispensableDrug drug = foundDrugs.get(0);
//        assertEquals("aspirin", drug.drugName());
//        assertFalse(drug.isControlled());
//        assertEquals(2, drug.drugClassifications().length);
//        assertArrayEquals(expectedClassifications, drug.drugClassifications());
        assertAll("DispensableDrug properties",
                () -> assertEquals("aspirin", drug.drugName()),
                () -> assertFalse(drug.isControlled()),
                () -> assertEquals(2, drug.drugClassifications().length),
                () -> assertArrayEquals(expectedClassifications, drug.drugClassifications())
        );
    }

//    @Override
//    public List<DrugRecord> findDrugsStartingWith(String startingString) {
//        List<DrugRecord> records = new ArrayList<>();
//        if(startingString.equals("as")) {
//            records.add(new DrugRecord("asmanex", new int[] {301}, 0));
//            records.add(new DrugRecord("aspirin", new int[] {3645, 3530}, 0));
//        } else if (startingString.equals("aspirin")){
//            records.add(new DrugRecord("aspirin", new int[] {3645, 3530}, 0));
//        }
//        return records;
//    }
}