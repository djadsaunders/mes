import { TestBed } from '@angular/core/testing';

import { ProductionRunService } from './production-run.service';

describe('ProductionRunService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ProductionRunService = TestBed.get(ProductionRunService);
    expect(service).toBeTruthy();
  });
});
