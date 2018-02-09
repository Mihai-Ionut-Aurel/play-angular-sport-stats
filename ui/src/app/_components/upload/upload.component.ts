import { Component, OnInit } from '@angular/core';
import {APIService} from "../../_services/_api/index"
import {environment} from "../../../environments/environment";
import { HttpEventType,HttpResponse} from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  moduleId: module.id,
  templateUrl: 'upload.component.html'
})

export class UploadComponent implements OnInit {

  constructor(private apiService : APIService, private router: Router,) { }

  ngOnInit() {

  console.log("Load upload component");
  }
  // At the drag drop area

  // (drop)="onDropFile($event)"
  onDropFile(event: DragEvent) {
    event.preventDefault();
    this.uploadFile(event.dataTransfer.files);
  }

  // At the drag drop area
  // (dragover)="onDragOverFile($event)"
  onDragOverFile(event) {
    event.stopPropagation();
    event.preventDefault();
  }

  // At the file input element
  // (change)="selectFile($event)"
  selectFile(event) {
    this.uploadFile(event.target.files);
  }

  uploadFile(files: FileList) {
    if (files.length == 0) {
      console.log("No file selected!");
      return

    }
    let file: File = files[0];

    this.apiService.uploadFile(`${environment.api}/${environment.upload}`, file)
      .subscribe(
        event => {
          if (event.type == HttpEventType.UploadProgress) {
            const percentDone = Math.round(100 * event.loaded / event.total);
            console.log(`File is ${percentDone}% loaded.`);
          } else if (event instanceof HttpResponse) {
            console.log('File is completely loaded!');
            this.router.navigate(['/competitions']);
          }
        },
        (err) => {
          console.log("Upload Error:", err);
        }, () => {
          console.log("Upload done");
        }
      )
  }

}
