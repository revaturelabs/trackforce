import { Associate } from "./associate.model";
import { EndClient } from './end-client.model';


export class Interview {
   id: number;
   associate: Associate;
   endClient: EndClient;
   typeId: number;
   typeName: string;
   dateNotified: Date;
   dateOfInterview: Date;
   jobDescription: string;
   associateFeedback: string;
   clientFeedback: string;
   questions: string;

}