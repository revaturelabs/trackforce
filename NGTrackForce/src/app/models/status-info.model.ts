/**
 * Used in the client model and the client-list component
 * Note made 6/7/2018
 * Reviewed by Max
 */

export class StatusInfo {
  name: string;
  trainingMapped: number; //1
  trainingUnmapped: number; //6
  reservedMapped: number; //2
  openUnmapped: number; //7
  selectedMapped: number; //3
  selectedUnmapped: number; //8
  confirmedMapped: number; //4
  confirmedUnmapped: number; //9
  deployedMapped: number; //5
  deployedUnmapped: number; //10
  constructor() { }
}
