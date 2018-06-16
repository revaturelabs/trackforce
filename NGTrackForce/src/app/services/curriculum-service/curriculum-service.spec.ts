import { TestBed, inject, getTestBed } from "@angular/core/testing";
import { CurriculumService } from "./curriculum.service";
import { HttpClientTestingModule } from "@angular/common/http/testing";



describe('CurriculumService', () => {
    beforeEach(() => {
        TestBed.configureTestingModule({
            providers: [CurriculumService],
            imports: [
                HttpClientTestingModule
            ]
        });
    });

    it('should be created', inject([CurriculumService], (service: CurriculumService) => {
        expect(service).toBeTruthy();
    }));

    it('should return data for specified skillset id', () => {
        const service: CurriculumService = getTestBed().get(CurriculumService);

        getTestBed().compileComponents().then(() => {
            service.getSkillsetsForStatusID(1).subscribe((res) => {
                const data = res.data;
                // this service better be returning some data...
                expect(data).toBeTruthy();
                // ... and that data better have stuff in it...
                expect(data.length).toBeTruthy();
                // let's get the first record in data
                expect(data[0]).toBeTruthy();
                // it should be truthy...
            })
                .unsubscribe()
        })
    });

});
