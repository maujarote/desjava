/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anafile;

import java.util.Objects;

/**
 *
 * @author mjromero
 */
class ResultadoArchivos {
    private String nomArchivo;
    private String extArchivo;
    private String rutaArchivo;
    private String titulo;
    private String artista;
    private String compositor;
    private String genero;
    private String album;
    private String duracion;
    private String anio;
    private String track;
    private String bloqueo;

    public String getNomArchivo() {
        return nomArchivo;
    }

    public String getExtArchivo() {
        return extArchivo;
    }

    public String getRutaArchivo() {
        return rutaArchivo;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getArtista() {
        return artista;
    }

    public String getCompositor() {
        return compositor;
    }

    public String getGenero() {
        return genero;
    }

    public String getAlbum() {
        return album;
    }

    public String getDuracion() {
        return duracion;
    }

    public String getAnio() {
        return anio;
    }

    public String getTrack() {
        return track;
    }

    public void setNomArchivo(String nomArchivo) {
        this.nomArchivo = nomArchivo;
    }

    public void setExtArchivo(String extArchivo) {
        this.extArchivo = extArchivo;
    }

    public void setRutaArchivo(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public void setCompositor(String compositor) {
        this.compositor = compositor;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public void setTrack(String track) {
        this.track = track;
    }

    public String getBloqueo() {
        return bloqueo;
    }

    public void setBloqueo(String bloqueo) {
        this.bloqueo = bloqueo;
    }

    @Override
    public String toString() {
        return "ResultadoArchivos{" + "nomArchivo=" + nomArchivo + ", extArchivo=" + extArchivo + ", rutaArchivo=" + rutaArchivo + ", titulo=" + titulo + ", artista=" + artista + ", compositor=" + compositor + ", genero=" + genero + ", album=" + album + ", duracion=" + duracion + ", anio=" + anio + ", track=" + track + ", bloqueo=" + bloqueo + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 31 * hash + Objects.hashCode(this.nomArchivo);
        hash = 31 * hash + Objects.hashCode(this.extArchivo);
        hash = 31 * hash + Objects.hashCode(this.rutaArchivo);
        hash = 31 * hash + Objects.hashCode(this.titulo);
        hash = 31 * hash + Objects.hashCode(this.artista);
        hash = 31 * hash + Objects.hashCode(this.compositor);
        hash = 31 * hash + Objects.hashCode(this.genero);
        hash = 31 * hash + Objects.hashCode(this.album);
        hash = 31 * hash + Objects.hashCode(this.duracion);
        hash = 31 * hash + Objects.hashCode(this.anio);
        hash = 31 * hash + Objects.hashCode(this.track);
        hash = 31 * hash + Objects.hashCode(this.bloqueo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ResultadoArchivos other = (ResultadoArchivos) obj;
        if (!Objects.equals(this.nomArchivo, other.nomArchivo)) {
            return false;
        }
        if (!Objects.equals(this.extArchivo, other.extArchivo)) {
            return false;
        }
        if (!Objects.equals(this.rutaArchivo, other.rutaArchivo)) {
            return false;
        }
        if (!Objects.equals(this.titulo, other.titulo)) {
            return false;
        }
        if (!Objects.equals(this.artista, other.artista)) {
            return false;
        }
        if (!Objects.equals(this.compositor, other.compositor)) {
            return false;
        }
        if (!Objects.equals(this.genero, other.genero)) {
            return false;
        }
        if (!Objects.equals(this.album, other.album)) {
            return false;
        }
        if (!Objects.equals(this.duracion, other.duracion)) {
            return false;
        }
        if (!Objects.equals(this.anio, other.anio)) {
            return false;
        }
        if (!Objects.equals(this.track, other.track)) {
            return false;
        }
        if (!Objects.equals(this.bloqueo, other.bloqueo)) {
            return false;
        }
        return true;
    }

    

    
   }