import { Interview } from "./interview.model";

export class InterviewType {
    id: number;
    name: string;
    interview: Interview[];
    
    constructor(
        id: number,
        name: string,
        interview?: Interview[]
    ) {
        this.id = id;
        this.name = name;
        this.interview = interview;
    }

}