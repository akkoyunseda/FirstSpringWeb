package com.garanti.FirstSpringWeb.controller;

import com.garanti.FirstSpringWeb.model.Ders;
import com.garanti.FirstSpringWeb.model.Konu;
import com.garanti.FirstSpringWeb.model.Ogrenci;
import com.garanti.FirstSpringWeb.model.Ogretmen;
import com.garanti.FirstSpringWeb.repo.DersRepo;
import com.garanti.FirstSpringWeb.repo.OgretmenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(path = "ders")
// localhost:9090/FirstSpringWeb/ders
public class DersController
{
    @Autowired
    private DersRepo repo;

    /*public DersController()
    {
        this.repo = new DersRepo();
    }*/

    @GetMapping(path = "getAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Ders>> getAll()
    {
        // localhost:9090/FirstRestfulService/ders/getAll
        List<Ders> res = repo.getAll();
        if (res == null || res.size() == 0)
        {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        else
        {
            return ResponseEntity.ok(res);
        }
    }


    @GetMapping(path = "getByIdHeader", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Ders> getByIdHeader(@RequestHeader(name = "id") Integer id)
    {
        // localhost:9090/FirstRestfulService/ders/getById?id=1
        Ders res = repo.getById(id);
        if (res != null)
        {
            return ResponseEntity.ok(res);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @GetMapping(path = "getById", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Ders> getByIdQueryParam(@RequestParam(value = "id", required = true) Integer id)
    {
        // localhost:9090/FirstRestfulService/ders/getById?id=1
        Ders res = repo.getById(id);
        if (res != null)
        {
            return ResponseEntity.ok(res);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @GetMapping(path = "getById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Ders> getByIdPathParam(@PathVariable(value = "id") Integer id)
    {
        // localhost:9090/FirstRestfulService/ders/getById/1
        //b??t??n parametreleri vermek zorunday??z
        //consume restful servisin d????ardan alaca???? data t??r??n?? belirtir
        //produce web servisin d????ar??ya verece??i t??r?? belirtir
        Ders res = repo.getById(id);
        if (res != null)
        {
            return ResponseEntity.ok(res);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @PostMapping(path = "save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> save(@RequestBody Ders ders)
    {
        // localhost:9090/FirstRestfulService/ogretmen/save
        // {"name":"RestTest", "is_GICIK": true}
        if (repo.save(ders))
        /*if (repo.saveTransactional(new Ogretmen("transactional test", true), new Konu("transactional konu")))*/
        {
            return ResponseEntity.status(HttpStatus.CREATED).body("Ba??ar?? ile kaydedildi");
        }
        else
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ba??ar?? ile kaydedildi");
        }
    }

    @DeleteMapping(path = "deleteById/{id}")
    public ResponseEntity<String> deleteById(@PathVariable(value = "id") Integer id)
    {
        // localhost:9090/FirstRestfulService/ogretmen/deleteById/1
        if (repo.deleteById(id))
        {
            return ResponseEntity.ok("Ba??ar?? ile silindi");
        }
        else
        {
            return ResponseEntity.internalServerError().body("Ba??ar?? ile silinemedi");
        }
    }

    @DeleteMapping(path = "deleteByIdHeader")
    public ResponseEntity<String> deleteByIdHeader(@RequestHeader(value = "id") Integer id)
    {
        // localhost:9090/FirstRestfulService/ogretmen/deleteById/1
        if (repo.deleteById(id))
        {
            return ResponseEntity.ok("Ba??ar?? ile silindi");
        }
        else
        {
            return ResponseEntity.internalServerError().body("Ba??ar?? ile silinemedi");
        }
    }
}