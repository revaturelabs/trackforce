import { Batch } from "./batch.model";

export class BatchLocation {
    id: number;
    name: string;
    batches: Batch[];

    constructor(
        id: number,
        name: string,
        batches?: Batch[]    
    ) {
        this.id = id;
        this.name = name;
        this.batches = batches;
    }
}