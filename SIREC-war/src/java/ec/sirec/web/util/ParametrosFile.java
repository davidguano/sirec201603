package ec.sirec.web.util;

import java.io.Serializable;

/**
 *
 * @author Darwin Aldas
 */
//Clase File  sirve como entidad para los parametros del archivo
public class ParametrosFile implements Serializable {

    private String Name;
    private String mime;
    private long length;
    private byte[] data;
    private String tipo;

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
        int extDot = name.lastIndexOf('.');
        if (extDot > 0) {
            String extension = name.substring(extDot + 1);
            if ("bmp".equals(extension)) {
                mime = "image/bmp";
            } else if ("jpg".equals(extension)) {
                mime = "image/jpeg";
            } else if ("gif".equals(extension)) {
                mime = "image/gif";
            } else if ("png".equals(extension)) {
                mime = "image/png";
            } else if ("pdf".equals(extension)) {
                mime = "document/pdf";
            } else {
                mime = "image/unknown";
            }
        }
    }

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public String getMime() {
        return mime;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

}
