package rxwriter.drug;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DrugConceptTest {

    public final static DrugConcept TEST_CONCEPT = new DrugConcept(new DrugClassification[]{
            DrugClassification.ANTIANXIETY,
            DrugClassification.ANALGESICS_NARCOTIC,
            DrugClassification.NARCOTIC_ANTIHISTAMINE});

    @Test
    void drugBelongsInConceptIfOneClassesMatches() {
        DispensableDrug drug = new DispensableDrug("adrug",
                new DrugClassification[] {DrugClassification.ANALGESIC,
                DrugClassification.ANTIANXIETY}, false);

        assertTrue(TEST_CONCEPT.isDrugInConcept(drug));
    }

    @Test
    void drugNotInConceptIfNoClassesMatches() {
        DispensableDrug drug = new DispensableDrug("adrug",
                new DrugClassification[] {DrugClassification.ANALGESIC,
                        DrugClassification.NASAL_CORTICOSTEROIDS}, false);

        assertFalse(TEST_CONCEPT.isDrugInConcept(drug));
    }

}