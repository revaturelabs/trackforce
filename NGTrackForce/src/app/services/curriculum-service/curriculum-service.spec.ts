import { TestBed, inject, getTestBed, async, ComponentFixture } from "@angular/core/testing";
import { CurriculumService } from "./curriculum.service";
import { HttpClientTestingModule } from "@angular/common/http/testing";

fdescribe('CurriculumService Test Suite', () => {
    let service;
    let fixture: ComponentFixture<CurriculumService>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            providers: [CurriculumService],
            imports: [
                HttpClientTestingModule
            ]
        }).compileComponents();
    }));

    beforeEach(inject([CurriculumService], (theService: CurriculumService) =>{
        fixture = TestBed.createComponent(CurriculumService);
        service = fixture.debugElement.injector.get(theService);
    }));

    it('should be created', () => {
        expect(service).toBeTruthy();
    });

    it('should return data for specified skillset id', () => {
        service = getTestBed().get(CurriculumService);

        service.getSkillsetsForStatusID(1).subscribe((res) => {
            const data = res;
            // this service better be returning some data...
            expect(data).toBeTruthy();
            // ... and that data better have stuff in it...
            // expect(data.length).toBeTruthy();
            // // let's get the first record in data
            // expect(data[0]).toBeTruthy();
            // // it should be truthy...
        },
            error => console.error('Error in curriculum-services.spec.ts:', error.message)
        ).unsubscribe()
    })
});
