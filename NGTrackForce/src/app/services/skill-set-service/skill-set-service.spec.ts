import { TestBed, inject, getTestBed } from "@angular/core/testing";
import { SkillsetService } from "./skill-set.service";
import { HttpClientTestingModule } from "@angular/common/http/testing";



describe('SkillsetService', () => {
    beforeEach(() => {
        TestBed.configureTestingModule({
            providers : [SkillsetService],
            imports   : [
                HttpClientTestingModule
            ]
        });
    });

    it('should be created', inject([SkillsetService], (service : SkillsetService) => {
        expect(service).toBeTruthy();
    }));

    it('should return data for specified skillset id', () => {
        let service : SkillsetService = getTestBed().get(SkillsetService);

        getTestBed().compileComponents().then(() => {
            service.getSkillsetsForStatusID(1).subscribe((res) => {
                expect(res.data).toBeTruthy();
            })
        })
    });
});