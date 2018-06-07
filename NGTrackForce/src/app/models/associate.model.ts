/**
 * Object to hold data regarding an associate.
 * @author Alex, Xavier
 */
export class Associate {
    id: number;
    firstName: string;
    lastName: string;
    // marketing status id
    msid: number;
    marketingStatus: string;
    verified: string;
    // Client id
    clid: number;
    client: string;
    // Batch id ????
    bid: number;
    // Curriculum id
    curid: number;
    curriculumName: string;
    // end client id
    ecid: number;
    endClientName: string;
    batchName: string;
    // I don't think this field is used at all through out the program
    batchId: string;
    clientStartDate: Date;
    // approved by the trainer ????
    isApproved: number;
}
