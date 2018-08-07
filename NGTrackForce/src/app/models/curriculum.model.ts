import { Batch } from "./batch.model";

/**
 * - This model is only used in the data-sync.service
 * - The data-sync.service class is never used
 * Note made 6/6/2018
 * Reviewed by Max
 */
export class Curriculum {
  id: number;
  name: string;
  batches: Batch[];
  // Kirk: count is new. CurriculumJSON.java and CurriculumInfo have id and name
  //       but only CurriculumJSON.java has count.
  // Cameron: commenting out because it doesn't appear in the Java entities
  //count: number;
}
