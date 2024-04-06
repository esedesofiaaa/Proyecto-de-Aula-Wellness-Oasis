package model.citas;

import model.MotivoCita.Valoracion;

public class CitaValoracion extends Cita{



        private Valoracion motivoCitaValoracion;

        public CitaValoracion(String idPaciente, boolean pagado, boolean tomado, Valoracion motivoCitaValoracion) {
            super(idPaciente, pagado, tomado);
            this.motivoCitaValoracion = motivoCitaValoracion;
        }

        public Valoracion getMotivoCitaValoracion() {
            return motivoCitaValoracion;
        }

        public void setMotivoCitaValoracion(Valoracion motivoCitaValoracion) {
            this.motivoCitaValoracion = motivoCitaValoracion;
        }

        @Override
        public String toString() {
            return  "idPaciente='" + getIdPaciente() + '\'' +
                    ", pagado=" + isPagado() +
                    ", tomado=" + isTomado() +
                    ", motivo Cita=" + motivoCitaValoracion +
                    '}';
        }


}
