package edu.stanford.bmir.protege.web.server.bulkop;

import com.google.common.collect.ImmutableSet;
import edu.stanford.bmir.protege.web.server.change.ChangeListGenerator;
import edu.stanford.bmir.protege.web.server.index.AnnotationAssertionAxiomsBySubjectIndex;
import edu.stanford.bmir.protege.web.server.index.ProjectOntologiesIndex;
import edu.stanford.bmir.protege.web.shared.bulkop.NewAnnotationData;
import edu.stanford.bmir.protege.web.shared.bulkop.Operation;
import org.semanticweb.owlapi.model.OWLAnnotationProperty;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLEntity;

import javax.annotation.Nonnull;
import javax.inject.Inject;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Factory for creating {@link EditAnnotationsChangeListGenerator} instances.
 */
public class EditAnnotationsChangeListGeneratorFactory {

    @Nonnull
    private final OWLDataFactory dataFactory;

    @Nonnull
    private final ProjectOntologiesIndex projectOntologiesIndex;

    @Nonnull
    private final AnnotationAssertionAxiomsBySubjectIndex annotationAssertionsIndex;

    @Inject
    public EditAnnotationsChangeListGeneratorFactory(@Nonnull OWLDataFactory dataFactory,
                                                    @Nonnull ProjectOntologiesIndex projectOntologiesIndex,
                                                    @Nonnull AnnotationAssertionAxiomsBySubjectIndex annotationAssertionsIndex) {
        this.dataFactory = checkNotNull(dataFactory);
        this.projectOntologiesIndex = checkNotNull(projectOntologiesIndex);
        this.annotationAssertionsIndex = checkNotNull(annotationAssertionsIndex);
    }

    @Nonnull
    public ChangeListGenerator<Boolean> create(@Nonnull ImmutableSet<OWLEntity> entities,
                                             @Nonnull Operation operation,
                                             @Nonnull Optional<OWLAnnotationProperty> property,
                                             @Nonnull Optional<String> lexicalValueExpression,
                                             boolean lexicalValueExpressionIsRegEx,
                                             @Nonnull Optional<String> langTagExpression,
                                             @Nonnull NewAnnotationData newAnnotationData,
                                             @Nonnull String commitMessage) {
        return new EditAnnotationsChangeListGenerator(dataFactory,
                                                    projectOntologiesIndex,
                                                    annotationAssertionsIndex,
                                                    entities,
                                                    operation,
                                                    property,
                                                    lexicalValueExpression,
                                                    lexicalValueExpressionIsRegEx,
                                                    langTagExpression,
                                                    newAnnotationData,
                                                    commitMessage);
    }
} 