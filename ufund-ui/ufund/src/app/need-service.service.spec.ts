import { TestBed } from '@angular/core/testing';

import { NeedServiceService } from './need-service.service';

describe('NeedServiceService', () => {
  let service: NeedServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(NeedServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
