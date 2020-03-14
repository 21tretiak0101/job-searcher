import { TestBed } from '@angular/core/testing';

import { VacancyService } from './vacancies.service';

describe('VacancyServiceService', () => {
  let service: VacancyService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(VacancyService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
