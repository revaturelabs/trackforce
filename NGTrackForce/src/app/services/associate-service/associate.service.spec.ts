import { AssociateService } from "./associate.service";
import { TestBed, inject } from '@angular/core/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';

describe("AssociateService", () => {

    beforeEach(() => {
        TestBed.configureTestingModule({
            imports: [
                HttpClientTestingModule,
            ],
            providers: [
                AssociateService
            ]
        });
    });

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
                console.log('getAssociateByUserId FAILED');
            }
        );
    }));

});