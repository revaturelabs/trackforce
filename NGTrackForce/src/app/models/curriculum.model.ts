/**
 * - This model is only used in the data-sync.service
 * - The data-sync.service class is never used
 * Note made 6/6/2018
 * @author Amelia
 */
export class Curriculum {
  id: number;
  name: string;
  // Kirk: count is new. CurriculumJSON.java and CurriculumInfo have id and name
  //       but only CurriculumJSON.java has count.
  count: number;
}
