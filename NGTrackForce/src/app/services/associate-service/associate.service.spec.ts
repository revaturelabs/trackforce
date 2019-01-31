import { AssociateService } from "./associate.service";
import { TestBed, inject } from '@angular/core/testing';
import { HttpClient } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { Associate } from '../../models/associate.model';

fdescribe("AssociateService", () => {
    //let service: AssociateService;

    beforeEach(() => {
        TestBed.configureTestingModule({
            imports: [
                HttpClientTestingModule,
            ],
            providers: [
                AssociateService,
                //HttpClient
            ]
        });
        //service = TestBed.get(AssociateService);
    });

    // it("should be instantiated", () => {
    //     expect(service).toBeTruthy;
    // });

    it("should be instantiated", inject([AssociateService], (service: AssociateService) => {
            expect(service).toBeTruthy();
        })
    );

    it("should successfully fetch associate data", inject([AssociateService], (service: AssociateService) => {
        service.getAssociateByUserId(5).subscribe(
            data => {
                expect(data).toBeTruthy();
                console.log(data);
                //expect(data.id).toBeTruthy();
                //expect(data.marketingStatus).toBeTruthy();
            },
            error => {
                console.log('error');
            }
        );
    }));

});