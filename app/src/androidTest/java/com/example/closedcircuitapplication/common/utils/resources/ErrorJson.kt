package com.example.closedcircuitapplication.common.utils.resources

object ErrorJson {
    val generic400Json = """
        {
          "message": "endpoint not valid",
          "data": null,
          "errors": error 400
        }
    """

    val loginErrorJson = """
         {
          "message": "Email and Password does not match",
          "data": null,
          "errors": null
        }
    """
}