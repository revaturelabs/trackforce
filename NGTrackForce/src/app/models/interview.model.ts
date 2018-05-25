import { Associate } from "./associate.model";
import { EndClient } from './end-client.model';


export class Interview {
   id: number;
   associate: Associate;
   endClient: EndClient;
   typeId: number;
   typeName: string;
   interviewDate: Date;
   interviewFeedback: string;
}