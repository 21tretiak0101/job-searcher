import { TestBed } from '@angular/core/testing';

import { VacancyServiceService } from './vacancy-service.service';

describe('VacancyServiceService', () => {
  let service: VacancyServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(VacancyServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
