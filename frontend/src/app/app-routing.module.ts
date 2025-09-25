import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UploadFilesComponent } from './component/upload/upload.component';
import { AboutComponent } from './component/about/about.component';

const routes: Routes = [
  { path: '', component: UploadFilesComponent },   
  { path: 'about', component: AboutComponent } 
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
