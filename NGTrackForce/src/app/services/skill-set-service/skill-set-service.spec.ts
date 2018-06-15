import { TestBed, inject, getTestBed } from "@angular/core/testing";
import { SkillsetService } from "./skill-set.service";
import { HttpClientTestingModule } from "@angular/common/http/testing";



describe('SkillsetService', () => {
    beforeEach(() => {
        TestBed.configureTestingModule({
            providers: [SkillsetService],
            imports: [
                HttpClientTestingModule
            ]
        });
    });

    it('should be created', inject([SkillsetService], (service: SkillsetService) => {
        expect(service).toBeTruthy();
    }));

    it('should return data for specified skillset id', () => {
        const service: SkillsetService = getTestBed().get(SkillsetService);

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