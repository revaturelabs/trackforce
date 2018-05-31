import { Associate } from "./associate.model";
import { EndClient } from './end-client.model';


export class Interview {
   id: number;
   associate: Associate;
   client: EndClient;
   typeId: number;
   type: string;
   DInterview: Date;
   AFeedback: string;
   CFeedBack: string;
   date: Date;
   JDescription: string;
   Flag: boolean;
}