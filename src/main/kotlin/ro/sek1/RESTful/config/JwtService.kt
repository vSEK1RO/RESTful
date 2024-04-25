package ro.sek1.RESTful.config

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import ro.sek1.RESTful.database.entity.User
import java.util.*
import javax.crypto.SecretKey

@Service
class JwtService {
    private val privateKey = "HLR25uictLAHLqkAg4LcsaFsRWousCOk3FIT3N4rAXOWX373iRCWAcvav5hNEy5luFl26DmMsjGGFoSCOHcf8HIGMtdQsldaCRAFvpcSMdON8s3j7xgDMbJDtmV1ygyb0wcm9wOKbGzaTlV3RIPv4HJp4ieUrLXx3nsLt08PmsOFof4RHyxgpu83COvpRZMS1IpvXrZlwRMTKefIgm7Xts2S2p28SdxhLEQqhGvtydhHIeO1Nty0kv5OqpXnhZPQPKjGA2uORFHujOMhdcaDG7gQLOwr05euxZanST9CisqfUZLlLaaaa6S88QpwzIZeZX5LzW0vWZsxc72eLdf"

    fun extractUsername(token: String): String{
        return extractClaim(token, Claims::getSubject)
    }
    fun isValid(token: String, user: UserDetails): Boolean{
        return extractUsername(token).equals(user.username) && !isTokenExpired(token)
    }
    fun isTokenExpired(token: String): Boolean{
        return extractClaim(token, Claims::getExpiration).before(Date())
    }
    fun <T> extractClaim(token: String, resolver: (Claims) -> T): T{
        return resolver(extractAllClaims(token))
    }
    fun extractAllClaims(token: String): Claims{
        return Jwts
            .parser()
            .verifyWith(getSigningKey())
            .build()
            .parseSignedClaims(token)
            .payload
    }
    val day_t: Long = 24*60*60*1000
    val hour_t: Long = 60*60*1000
    val min_t: Long = 60*1000
    val sec_t: Long = 1000
    fun generateToken(user: User): String{
        return Jwts
            .builder()
            .subject(user.name)
            .issuedAt(Date(System.currentTimeMillis()))
            .expiration(Date(System.currentTimeMillis()+10*min_t))
            .signWith(getSigningKey())
            .compact()
    }
    fun getSigningKey(): SecretKey{
        return Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(privateKey))
    }
}