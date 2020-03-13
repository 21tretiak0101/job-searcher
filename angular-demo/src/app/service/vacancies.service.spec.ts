import { TestBed } from '@angular/core/testing';

import { VacancyServiceService } from './vacancies.service';

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
