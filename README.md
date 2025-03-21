# Karya

### API Endpoints

| Endpoint                                 | Method | Usage                                      |
|------------------------------------------|--------|---------------------------------------------|
| `/auth/register`                         | POST   | Register a new user                         |
| `/auth/login`                            | POST   | User login                                  |
| `/auth/assign-subvendor`                 | POST   | Assign a sub-vendor to a main vendor        |
| `/auth/subvendors/{vendorId}`            | GET    | Get all sub-vendors for a vendor            |
| `/vehicles/vendor/{vendorId}`            | POST   | Add a vehicle for a specific vendor         |
| `/vehicles/vendor/{vendorId}`            | GET    | Get all vehicles for a specific vendor      |
