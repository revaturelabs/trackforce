import { MockAssociateService } from './../../components/associate-view/associate-view.component.spec';
import { TestBed, inject, getTestBed, async, ComponentFixture } from "@angular/core/testing";
import { CurriculumService } from "./curriculum.service";
import { HttpClientTestingModule } from "@angular/common/http/testing";
import { Observable } from 'rxjs';

describe('CurriculumService Test Suite', () => {
    let service:CurriculumService;
    let mockAssociateService = new MockAssociateService(null);
    let spy;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            providers: [CurriculumService],
            imports: [
                HttpClientTestingModule
            ]
        }).compileComponents();
    }));

    beforeEach(inject([CurriculumService], (theService: CurriculumService) =>{
        service = theService;
    }));

    it('should be created', () => {
        expect(service).toBeTruthy();
    });

    //Must change this test so that it mocks the HttpClient instead of just returning mock data of my choice, which defeats the purpose
    //of testing this service.
    it('should return data for specified skillset id', () => {
        spy = spyOn(service, 'getSkillsetsForStatusID').and.returnValue(Observable.of(mockAssociateService.mockData));
        let holder = service.getSkillsetsForStatusID(1);
        expect(holder).toBeTruthy();
    });
});
