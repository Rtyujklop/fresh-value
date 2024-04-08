import { TestBed } from '@angular/core/testing';
import { CanActivateChildFn } from '@angular/router';

import { checkoutGuard } from './checkout.guard';

describe('checkoutGuard', () => {
  const executeGuard: CanActivateChildFn = (...guardParameters) => 
      TestBed.runInInjectionContext(() => checkoutGuard(...guardParameters));

  beforeEach(() => {
    TestBed.configureTestingModule({});
  });

  it('should be created', () => {
    expect(executeGuard).toBeTruthy();
  });
});
