import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NeedDetailAdminComponent } from './need-detail-admin.component';

describe('NeedDetailAdminComponent', () => {
  let component: NeedDetailAdminComponent;
  let fixture: ComponentFixture<NeedDetailAdminComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [NeedDetailAdminComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(NeedDetailAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
