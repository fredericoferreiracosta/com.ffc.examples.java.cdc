import org.springframework.cloud.contract.spec.Contract
Contract.make {
    description "should return product"
    request {
        method GET()
        url("/product/1")
    }
    response {
        body('{"id":"1","name":"Product 1"}')
        status 200
    }
}