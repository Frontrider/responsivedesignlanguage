package hu.rdl.rdl.language

import hu.rdl.rdl.language.objects.MediaObject


fun validateMedia(mediaObject: MediaObject)
{
    val errors = ArrayList<ErrorDetail>()



    throw InvalidMediaTagException(errorDetail = errors)
}